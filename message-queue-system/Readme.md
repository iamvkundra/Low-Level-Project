# Message queueing system
#### Develop a message queueing system. Functional requirements of this system has been described below in detail.

 - Create your own queue that will hold messages in form of JSON. Standard library queues were not allowed.
 - There was one publisher that can generate messages.
 - There are mutiple suscribers that will listen messages satisfying a particular regex.
 - Suscribers should not be tighly coupled to system and can be added or removed at runtime.
 - When a suscriber is added to the system, it registers callback function along with it. And this callback function will be invoked in case some message arrives.
 - There can be dependency relationship among suscribers i.e if there are two suscribers say A and B and A knows that B has to listen and process first, then only A can listen and process. There was many to many dependency relationship among suscribers.
 - There must a retry mechanism for handling error cases when some exception occurs in listening/ processing messages, that must be retried._____

My solution github link : https://github.com/shecodes29/Design-interviews/tree/master/PubSubSystem
I am open to any review comments and further enhancements in design. Thanks.