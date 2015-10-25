# readme




middle end :


aim : provide encapsultate information on request

how : 
on a request we can make request ( class encapsulate a json message),
the middle end instantly provide builder<Class> containing data currently downloaded from the endHost.
when a builder has downloaded all the instance, isLoaded() become true and builder can use his
build() => Class method.



front end : 


aim : display information and manage view


how :


machine state : state of the application (AUTHENTIFICATION, WIFI, ...)

controler : observe activity event and make action on it : setState(controlerState) 


when a state of one machine observed evolve execute { 

if it exists requests for the currents controlerStates of my machines

ask middle end :  RetreieveDataBuildable on : requests

update view whith when theRetreieveDataBuildable are ready and update state in a machine if necessary. 


}



( read line )


PACKAGE : MIDDLE END 

1 Util : signature, MacAddrSignature, Device

2 RetrieveData : RelatedRoom, MusicData

3 Parser : BooleanParser 

4 Network : Request, BackendTask

PACKAGE : FRONT END 

5 MachineState : Authentification 

6 Util : Controler


