# readme



    # middle end



NETWORK :


message : decorate a json message (can be a request or a response)

Request extends message : include a static factory to create request

pending data : class running in background :

1. make request (passed in constructor) => answer

2. use parser (passed in constructor) to transform answer in a T instance

3. mark the boolean private variable : isLoaded as true




PARSER :

used by pending data to provide a representative class from a Message(response)



RETRIEVE DATA :

representative class of a message response made by the backend.





    # front end





ACTIVITY :


contains setter to display stuff
can modify the state of the application when a button is pressed (see below)



CONTROLER :


controler : control a specific view
is notified when a state of the application has changed
make request to the backend and use the activity seteur to update the view with data

state : almost an integer
machineState :
has a type : Connection/display/
setter to change is currentState and notify observers(controler) that a state is modified.

staticMachine :
declare private static instance of machine state (same machine for all app)
provide a setter (machineType, newCurrentState)










