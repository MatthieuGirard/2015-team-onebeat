import json
from django.shortcuts import render
from django.shortcuts import redirect
from django.http import JsonResponse
from .models import User
from .models import Song
from .models import Room
from .models import Playlist
from .models import Member
	
#def existUser(request):
#	received_json_data=json.loads(request.GET['request'])
#	userId=received_json_data['id']
#	if (User.objects.filter(userId=userId).exists()):
#		return JsonResponse({'existUser':'true'})
#	else:
#		return JsonResponse({'existUser':'false'})

def addUser(request):
	received_json_data = json.loads(request.POST['request'])
	userId = received_json_data['id']
	name = received_json_data['name']
	User.objects.create(
		userId = userId,
		name = name
		)
	return JsonResponse({
		'added':'true',
		'user':name
		})

def getUser(request):
	received_json_data = json.loads(request.GET['request'])
	userId = received_json_data['id']
	user = User.objects.get(userId=userId)
	return JsonResponse({
		'info': 'user',
		'id':userId,
		'pseudo':user.name
		})

#def existSong(request):
#	received_json_data=json.loads(request.GET['request'])
#	songId=received_json_data['id']
#	if (Song.objects.filter(songId=songId).exists()):
#		return JsonResponse({'existSong':'true'})
#	else:
#		return JsonResponse({'existSong':'false'})

def addSong(request):
	received_json_data = json.loads(request.POST['request'])
	songId = received_json_data['id']
	title = received_json_data['title']
	if (not Song.objects.filter(songId = songId).exists()):
		artist = received_json_data['artist']
		duration = received_json_data['duration']
		spotifyRef = received_json_data['spotifyRef']
		addedBy = received_json_data['addedBy']
		Song.objects.create(
			songId = songId,
			artist = artist,
			title = title,
			duration = duration,
			spotifyRef = spotifyRef
			addedBy = addedBy
			)
	return JsonResponse({
		'added':'true',
		'song':title
		})

def getSong(request):
	received_json_data = json.loads(request.GET['request'])
	songId = received_json_data['id']
	song = Song.objects.get(songId=songId)
	return JsonResponse({
		'info' : 'song', 
		'id' : song.songId,
		'artist' : song.artist, 
		'title' : song.title, 
		'duration' : song.duration, 
		'spotifyRef' : song.spotifyRef,
		'addedBy' : song.addedBy
		})

def getRoom(request):
	received_json_data = json.loads(request.GET['request'])
	roomId = received_json_data['id']
	room = Room.objects.get(roomId = roomId)
	return JsonResponse({
		'info' : 'room',
		'id' : room.roomId,
		'creator' : room.creator,
		'name' : room.name,
		'playlist' : room.playlist,
		'addedBy' : room.addedBy,
		'members' : room.members
		})

def createRoom(request):
	received_json_data = json.loads(request.POST['request'])
	roomID = received_json_data['id']
	roomName = received_json_data['name']
	if (not Room.objects.filter(roomName = roomName).exists()):
		creator = received_json_data['creator']
		name = received_json_data['name']
		Song.objects.create(creator = creator, name = name)
	return JsonResponse({
		'creator' : room.creator,
		'name' : room.name
		})

def joinRoom(request):
	received_json_data=json.loads(request.POST['request'])
	roomID = received_json_data['id']
	roomName = received_json_data['name']
	if (Room.objects.filter(roomName = roomName).exists()):
		return JsonResponse({
			'name' : room.name
			})


def addMember(request):
	received_json_data = json.loads(request.POST['request'])
	roomID = received_json_data['user']
	roomName = received_json_data['room']
	if (Room.objects.filter(roomName = roomName).exists()):
		return JsonResponse({
			'user' : room.name
			'room' : room.room
			})




