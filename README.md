# readme



# middle end 


the aim of the package is to provide encapsulated information on request.
A Request(encapsulated JSON) can retrieve data from backend : Boolean/ User/ Rooms/...
in pending mode (encapsulated in PendingData<T>).
The class asking for pendingData need to wait until isLoaded() become true
to extract the data T from the PendingData<T> instance.
(PendingData).


# front end 


the aim is to display information and manage view.
Many static BoardState give information about the current state of an app :
activity/authentification...
When an event occurs on the view the activity has just to update the current
state of one of the static boardState. The activity didn't modify his display,
it only provide method to modify his display.
it's possible for an observer to subscribe to a boardState, and get notified
when the currentState has changed.
It's the case of the controler that encapsulate an activity and when the board
state change his currentState get notified, make request for pendingData,
wait for data loaded and parametrize the activity.

exemple  :

activity : buttonConnect clicked{

BOARDState.AUTHENTIFICATION enter state : TryConnect(  BoardState =>  notifyObserver(); )

}

ControlerMainActivity : isNotified by boardState

=> display a loading gif on the activity

=> get boolean for success connection

=> if(boolean == false) not connected : retry  else :

=> undisplay the loading gif on the main activity

=> BOARDState.Display enter state : MY_ROOM_ACTIVITY(  BoardState =>  notifyObserver(); )




# fast understanding read line 


PACKAGE : MIDDLE END 

1 Util : signature, MacAddrSignature, Device

2 RetrieveData : PendingData

3 Network : DownloadData 

4 RetrieveData : RoomData 

5 Network : Request, PendingDataProvider

PACKAGE : FRONT END 

6 MachineState : Authentification 

7 Util : Controler




