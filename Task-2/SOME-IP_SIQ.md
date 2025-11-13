# **SOME/IP - SIQ**

## **Which device is server & Which is client for {SOME/IP}**
- Server will be QNX that being run on V.M in my PC {Update Provider}
- Client will be Rpi 4 {Update Target}
- My PC {Update Builder}

## **My intial state flow of communication for whole system**
![image](7.svg)


## **What is the Method in {SOME/IP}:**
- A method, procedure, function, or subroutine that is called/invoked.
- A service can consist of combinations of zero or multiple events, methods and fields.
- Methods provide the possibility to the subscriber to issue remote procedure calls which are executed on provider side.
- The Message ID shall be a 32 Bit identifier that is used to identify: ![image](4.png)
    - the RPC call to a method of an application
    - or to identify an event.
    - [32 Bits] == {Service ID [16 bit] + [1 bit] + Event ID / Method ID [15 bit]} ![image](5.png)
- Method ID [15 Bit] Message IDs of method calls
- Payload might consists of data elements for events or parameters for methods.

## **What is the Event in {SOME/IP}:**
- A service can consist of combinations of zero or multiple events, methods and fields.
- Events provide data that are sent cyclically or on change from the provider to the subscriber.
- The major difference between the notifier of a field and an event is that events are only sent on change, the notifier of a field additionally sends the data directly after subscription.
- The Message ID shall be a 32 Bit identifier that is used to identify:
    - the RPC call to a method of an application
    - or to identify an event.
    - [32 Bits] == {Service ID [16 bit] + [1 bit] + Event ID / Method ID [15 bit]} ![image](6.png)
- Event ID [15 Bit] Eventgroup is a logical grouping of events and notification events of fields inside a service in order to allow subscription
- Payload might consists of data elements for events or parameters for methods.

## **What is the Eventgroup in {SOME/IP}:**
- A logical grouping of events and notification events of fields inside a service in order to allow subscription
- Event ID [15 Bit]: Eventgroup is a logical grouping of events and notification events of fields inside a service in order to allow subscription.

## **What is the Subscribe in {SOME/IP}:**
- Notifications describe a general Publish/Subscribe-Concept, Usually the server publishes a service to which a client subscribes, On certain cases the server will send the client an event, which could be for example an updated value or an event that occurred.
- Methods provide the possibility to the subscriber to issue remote procedure calls which are executed on provider side.
- Fields are combinations of one or more of the following three
    - a notifier which sends data on change from the provider to the subscribers
    - a getter which can be called by the subscriber to explicitly query the provider for the value
    - a setter which can be called by the subscriber when it wants to change the value on provider side
 
## **When i need to subscribe in {SOME/IP}:**
- if i as client want to be notified if the server has a new update for example
- The notifier shall send an event message that transports the value of the field to the client when the client subscribes to the field

