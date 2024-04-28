import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicInteger;


enum IssueType {
    PAYEMENT,
    MUTUAL_FUND,
    GOLD,
    INSURANCE
}

enum ResolutionStatus {
    ASSIGNED,
    UNASSIGNED,
    RESOLVED
}

interface AgentSelecter {
    String assignAgent(IssueType issueType);
}

interface IssueObserver {
    void updated(Issue issue);
}

interface IssueSubject {
    void attach(IssueObserver observer);
    void notifyAll(Issue issue);
}

interface ResolutionInterface{
    boolean createIssue(Issue issue);
    boolean assignIssue(String issueId, int assignDecisionType);
    void resolveIssue(String issueId, String resolution);
    String addAgent(String agentId, List<IssueType> expertise);
    List<String> getAgentHistory(String agentId);
}

public class Main implements ResolutionInterface {
    private IssueManager issueManager;
    private AgentAssigner agentAssigner;
    private AgentsManager agentManager;
    public Main(){
        issueManager = new IssueManager();
        agentManager = new AgentsManager();
        agentAssigner = new AgentAssigner(
                issueManager, agentManager);
    }

    public boolean createIssue(Issue issue) {
        if (issue == null) {
            System.out.println("invalid issue type");
            return false;
        }
        boolean status  = issueManager.createIssue(issue);
        if(!status) {
            System.out.println("Already present issue");
            return false;
        }
        return status;
    }

    public void resolveIssue(String issueId, String resolution) {
        Issue issue = issueManager.getIssue(issueId);
        if(issue==null||issue.isResolved() || !issue.isAssigned()) return;
        var agent = agentManager.getAgent(issue.getAgentId());
        agent.addResolveIssueId(issueId);
        issueManager.resolveIssue(issueId, resolution);
        System.out.println("issueId: "+ issueId + " marked resolved. resolution: " + resolution);
    }

    // returns success, agent already exists
    public String addAgent(String agentId,  List<IssueType> expertise) {

        String status = agentManager.addAgent(agentId, expertise);
        if (status.equals("success")) {
            System.out.println("Agent successfully created:  agentId: "+ agentId);
            return agentId;

        }
        return "";
    }

    public List<String> getAgentHistory(String agentId) {
        var agent= agentManager.getAgent(agentId);
        if(agent==null) return new ArrayList<>();
        return agent.getResolvedIssues();
    }

    public boolean assignIssue(String issueId, int assignStrategy) {
        Issue issue = issueManager.getIssue(issueId);
        if(issue==null) {
            System.out.println("issue doesn't exist");
            return false;
        }
        if(issue.isAssigned()) {
            System.out.println("issue already assigned");
            return false;
        }
        String agentId = agentAssigner.getAgentForAssigningIssue(issue, 0);
        if(agentId.isEmpty()) {
            System.out.println("agent with expertise doesn't exist");
            return false;
        }
        issueManager.assignIssue(issueId, agentId);
        System.out.println("Issue assigned: issueId: " + issueId + " agentId: "+ agentId);
        return true;
    }

    public static void main(String[] args) {
        Main main = new Main();

        // Add an agent
        String agentId1 = main.addAgent("paymentAgent", new ArrayList<>(List.of(IssueType.PAYEMENT)));
        String agentId2 = main.addAgent("mutualAgent", new ArrayList<>(List.of(IssueType.MUTUAL_FUND)));


        Issue paymentIssue = new PayementIssue("P_1","T1", "Payment money got deducted", "payment failed", "xyz@gmail.com");
        Issue mutualIssue = new MutualFundIssue("MF_1","T2", "Payment money got deducted", "payment failed", "xyz@gmail.com");

        // Create an issue
        main.createIssue(paymentIssue);


        //Assign issue
        main.assignIssue("P_1", 0);

        // Resolve the issue
        main.resolveIssue("P_1", "Payment amount will be reverted back to the same account");



        // Get agent's history
        List<String> agentHistory = main.getAgentHistory(agentId1);
        System.out.println("Agent History: " + agentHistory);


        agentHistory = main.getAgentHistory(agentId2);
        System.out.println("Agent History: " + agentHistory);
    }

}

class AgentsManager {
    // issueType vs list of agentIds
    private final HashMap<IssueType, ConcurrentLinkedDeque<String>> agentSkillsMap;
    private final ConcurrentHashMap<String, Agent> agents;

    AgentsManager(){
        agentSkillsMap = new HashMap<>();
        agents=new ConcurrentHashMap<>();
        // initialize all issueTypes i.e skills
        agentSkillsMap.put(IssueType.GOLD, new ConcurrentLinkedDeque<>());
        agentSkillsMap.put(IssueType.INSURANCE, new ConcurrentLinkedDeque<>());
        agentSkillsMap.put(IssueType.MUTUAL_FUND, new ConcurrentLinkedDeque<>());
        agentSkillsMap.put(IssueType.PAYEMENT, new ConcurrentLinkedDeque<>());
    }

    public Agent getAgent(String agentId){
        return agents.get(agentId);
    }

    public String addAgent(String agentId,  List<IssueType> expertise) {
        if(agents.containsKey(agentId)) return "Agent already exist";
        agents.put(agentId, new Agent(agentId, expertise));
        for(IssueType skill: expertise){
            Collection<String> agentIds = agentSkillsMap.get(skill);
            agentIds.add(agentId);
        }
        return "success";
    }

    Collection<String> getAgentIdsForIssue(IssueType issueType){
        Collection<String> agents = agentSkillsMap.getOrDefault(
                issueType, new ConcurrentLinkedDeque<>());
        return Collections.unmodifiableCollection(agents);
    }
}

class IssueManager implements IssueSubject {
    private final ArrayList<IssueObserver> observeAllIssues = new ArrayList<>();
    private final ConcurrentHashMap<String, Issue> allIssues= new ConcurrentHashMap<>();

    /**
     * @return returns "issue created", "issue already exists"
     */
    public boolean createIssue(Issue issue) {
        if(allIssues.containsKey(issue.getIssueId()))  return false;
        allIssues.put(issue.getIssueId(), issue);
        System.out.println("Issue Created: issueId: " + issue.getIssueId());
        return true;
    }

    public void resolveIssue(String issueId, String resolution) {
        Issue issue = allIssues.get(issueId);
        if(issue==null) return;
        issue.resolveIssue(resolution);
        notifyAll(issue);
    }

    public Issue getIssue(String issueId){
        return allIssues.getOrDefault(issueId, null);
    }

    void assignIssue(String issueId, String agentId) {
        Issue issue = allIssues.get(issueId);
        if(issue==null) return;
        issue.assignIssue(agentId);
        notifyAll(issue);
    }

    public void attach(IssueObserver observer) {
        observeAllIssues.add(observer);
    }

    public void notifyAll(Issue issue) {
        for(IssueObserver observer: observeAllIssues)
            observer.updated(issue);
    }
}

class Agent{
    private String agentId;
    private List<IssueType> skills;
    private ArrayList<String> resolvedIssues;

    Agent(String agentId, List<IssueType> skills){
        this.agentId=agentId;
        this.skills=skills;
        resolvedIssues=new ArrayList<>();
    }

    public List<String> getResolvedIssues(){
        return resolvedIssues;
    }

    public synchronized void addResolveIssueId(String issueId){
        resolvedIssues.add(issueId);
    }
}

abstract class Issue {
    String issueId;
    String transactionId;
    String subject;
    String description;
    String emailId;
    IssueType issueType;

    String agentId, resolution;

    private ResolutionStatus status;

    public Issue(String issueId, String transactionId, IssueType issueType, String subject, String description, String emailId) {
        this.issueId = issueId;
        this.transactionId = transactionId;
        this.description = description;
        this.issueType = issueType;
        this.subject = subject;
        this.emailId = emailId;
        agentId="";
        resolution="";
        status = ResolutionStatus.UNASSIGNED;
    }

    public String getAgentId(){
        return agentId;
    }

    public String getIssueId() {
        return issueId;
    }

    public IssueType getIssueType(){
        return issueType;
    }

    public void assignIssue(String agentId){
        this.agentId=agentId;
        this.status=ResolutionStatus.ASSIGNED;
    }

    public boolean isAssigned(){
        return agentId!=null && !agentId.isBlank();
    }

    public boolean isResolved(){
        return status==ResolutionStatus.RESOLVED;
    }

    public void resolveIssue(String resolution){
        this.resolution=this.resolution+" "+resolution;
        status=ResolutionStatus.RESOLVED;
    }
}

class PayementIssue extends Issue {
    public PayementIssue(String issueId, String transactionId, String subject, String description, String emailId) {
        super(issueId, transactionId, IssueType.PAYEMENT, subject, description, emailId);
    }
}

class MutualFundIssue extends Issue {
    public MutualFundIssue(String issueId, String transactionId, String subject, String description, String emailId) {
        super(issueId, transactionId, IssueType.MUTUAL_FUND, subject, description, emailId);
    }
}

class GoldIssue extends Issue {
    public GoldIssue(String issueId, String transactionId, String subject, String description, String emailId) {
        super(issueId, transactionId, IssueType.GOLD, subject, description, emailId);
    }
}

class InsuranceIssue extends Issue {
    public InsuranceIssue(String issueId, String transactionId, String subject, String description, String emailId) {
        super(issueId, transactionId, IssueType.INSURANCE, subject, description, emailId);
    }
}

class AgentAssigner {
    // assignStrategy vs AgentSelecter
    private HashMap<Integer, AgentSelecter> map = new HashMap<>();

    AgentAssigner(IssueSubject issueSubject,
                  AgentsManager agentManager){
        var lowestIssuesOpenAssigner =  new LowestIssuesOpenSelecter(agentManager);
        var experiencedAgentAssigner=new MostExperiencedAgentSelecter(
                agentManager);
        var traineeAgentAssigner = new TraineeAgentSelecter(agentManager);

        map.put(0, lowestIssuesOpenAssigner);
        map.put(1, experiencedAgentAssigner);
        map.put(2, traineeAgentAssigner);

        issueSubject.attach(lowestIssuesOpenAssigner);
        issueSubject.attach(experiencedAgentAssigner);
        issueSubject.attach(traineeAgentAssigner);
    }


    String getAgentForAssigningIssue(Issue issue, int assignStrategy){
        IssueType issueType = issue.getIssueType();
        AgentSelecter strategy = map.get(assignStrategy);
        if(strategy!=null)return strategy.assignAgent(issueType);
        return "";
    }
}


class LowestIssuesOpenSelecter implements AgentSelecter, IssueObserver {
    // agentId vs open issue count
    private final ConcurrentHashMap<String, AtomicInteger> agentOpenIssuesMap =
            new ConcurrentHashMap<>();
    private final AgentsManager agentsManager;

    LowestIssuesOpenSelecter(AgentsManager agentsManager){
        this.agentsManager=agentsManager;
    }

    // returns id of the agent or empty string i.e ""
    public String assignAgent(IssueType issueType) {
        String chosenAgentId ="";
        int minOpenIssues=1000*1000*1000;
        Collection<String> agentIds = agentsManager.getAgentIdsForIssue(issueType);
        for(String agentId: agentIds){
            AtomicInteger open= agentOpenIssuesMap.getOrDefault(
                    agentId, new AtomicInteger(0));
            if(open.get()==0) return agentId;
            if(open.get()<=minOpenIssues){
                minOpenIssues=open.get();
                chosenAgentId=agentId;
            }
        }
        return chosenAgentId;
    }

    public void updated(Issue issue) {
        if(!issue.isAssigned()) return;
        agentOpenIssuesMap.putIfAbsent(
                issue.getAgentId(), new AtomicInteger(0));
        AtomicInteger existing = agentOpenIssuesMap.get(issue.getAgentId());
        if(!issue.isResolved()) existing.addAndGet(1);
        else existing.addAndGet(-1);
    }

}

class MostExperiencedAgentSelecter implements AgentSelecter, IssueObserver {
    // issueType vs (agentId vs number of resolved issues of type issueType)
    private final HashMap<IssueType, ConcurrentHashMap<String, AtomicInteger>> issueTypeAgentResolvedCountMap =
            new HashMap<>();
    private final AgentsManager agentsManager;

    MostExperiencedAgentSelecter(
            AgentsManager agentsManager){
        this.agentsManager=agentsManager;

        issueTypeAgentResolvedCountMap.put(IssueType.PAYEMENT, new ConcurrentHashMap<>());
        issueTypeAgentResolvedCountMap.put(IssueType.GOLD, new ConcurrentHashMap<>());
        issueTypeAgentResolvedCountMap.put(IssueType.MUTUAL_FUND, new ConcurrentHashMap<>());
        issueTypeAgentResolvedCountMap.put(IssueType.INSURANCE, new ConcurrentHashMap<>());
    }

    // returns id of the agent or empty string i.e ""
    public String assignAgent(IssueType issueType) {
        ConcurrentHashMap<String, AtomicInteger> agentResolvedIssuesCountMap =
                issueTypeAgentResolvedCountMap.get(issueType);
        Collection<String> agentIds = agentsManager.getAgentIdsForIssue(issueType);

        //if(agentResolvedIssuesCountMap==null || agentResolvedIssuesCountMap.size()==0) return "";
        String chosenAgentId ="";
        int maxResolvedIssues=-1;
        for(String agentId: agentIds){
            AtomicInteger issuesResolved= agentResolvedIssuesCountMap.getOrDefault(agentId, new AtomicInteger(0));
            if(issuesResolved.get()>=maxResolvedIssues){
                maxResolvedIssues=issuesResolved.get();
                chosenAgentId=agentId;
            }
        }
        return chosenAgentId;
    }

    public void updated(Issue issue) {
        if(!issue.isResolved()) return;
        ConcurrentHashMap<String, AtomicInteger> agentResolvedIssuesCountMap =
                issueTypeAgentResolvedCountMap.get(issue.getIssueType());
        agentResolvedIssuesCountMap.putIfAbsent(
                issue.getAgentId(), new AtomicInteger(0));
        AtomicInteger existing = agentResolvedIssuesCountMap.get(issue.getAgentId());
        existing.addAndGet(1);
    }
}

class TraineeAgentSelecter implements AgentSelecter, IssueObserver {
    // issueType vs (agentId vs number of open issues of type issueType)
    // using Integer to store count rather than AtomicInteger
    private final HashMap<IssueType, ConcurrentHashMap<String, Integer>> issueTypeAgentOpenCountMap =
            new HashMap<>();
    private final AgentsManager agentsManager;

    TraineeAgentSelecter(AgentsManager agentsManager){
        this.agentsManager=agentsManager;
        issueTypeAgentOpenCountMap.put(IssueType.PAYEMENT, new ConcurrentHashMap<>());
        issueTypeAgentOpenCountMap.put(IssueType.GOLD, new ConcurrentHashMap<>());
        issueTypeAgentOpenCountMap.put(IssueType.MUTUAL_FUND, new ConcurrentHashMap<>());
        issueTypeAgentOpenCountMap.put(IssueType.INSURANCE, new ConcurrentHashMap<>());

    }

    public String assignAgent(IssueType issueType) {
        Collection<String> agentIds = agentsManager.getAgentIdsForIssue(issueType);
        ConcurrentHashMap<String, Integer> agentOpenIssues =
                issueTypeAgentOpenCountMap.get(issueType);
        String chosenAgentId ="";
        int minOpenIssues=1000*1000*1000;

        for(String agentId: agentIds){
            Integer open = agentOpenIssues.getOrDefault(agentId, 0);
            if(open==0) return agentId;
            if(open<=minOpenIssues){
                minOpenIssues=open;
                chosenAgentId=agentId;
            }
        }

        return chosenAgentId;
    }

    public void updated(Issue issue) {
        if(!issue.isAssigned()) return;
        ConcurrentHashMap<String, Integer> agentOpenIssues =
                issueTypeAgentOpenCountMap.get(issue.getIssueType());

        agentOpenIssues.compute(issue.getAgentId(),
                (key, value)-> {
                    int next =value == null ? 0 :value;
                    return issue.isResolved() ? next - 1 : next + 1;
                });
    }
}



