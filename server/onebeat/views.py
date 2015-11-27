import json
from django.shortcuts import render
from django.shortcuts import redirect
from django.http import JsonResponse
from .models import User
from .models import Song
from .models import Room
from .models import Playlist
from .models import Member

def addUser(request):
	received_json_data = json.loads(request.POST['request'])
	userId = received_json_data['id']
	name = received_json_data['name']
	if (User.objects.filter(userId = userId).exists()):
		return JsonResponse({
			'error':'user already exists',
			'id' : userId
			})
	else:
		User.objects.create(
			userId = userId,
			name = name
			)
		return JsonResponse({
			'added':True,
			'user':userId
			})

def getUser(request):
	received_json_data = json.loads(request.GET['request'])
	userId = received_json_data['id']
	if (User.objects.filter(userId = userId).exists()):
		user = User.objects.get(userId = userId)
		rooms = Member.objects.filter(user = userId)
		return JsonResponse({
			'info':'user',
			'id':userId,
			'name':user.name,
			'rooms':rooms
			})
	else:
		return JsonResponse({
			'error':'user does not exist',
			'id' : userId
			})

def addSong(request):
	received_json_data = json.loads(request.POST['request'])
	songId = received_json_data['id']
	if (Song.objects.filter(songId = songId).exists()):
		return JsonResponse({
			'error':'song already exists',
			'id' : songId
			})
	else:
		#how are songs added to the room's playlist?
		#and how is the addedBy list compiled when ppl add songs?
		title = received_json_data['title']
		artist = received_json_data['artist']
		duration = received_json_data['duration']
		spotifyRef = received_json_data['spotifyRef']
		addedBy = received_json_data['addedBy']
		Song.objects.create(
			songId = songId,
			artist = artist,
			title = title,
			duration = duration,
			spotifyRef = spotifyRef,
			addedBy = addedBy
			)
		return JsonResponse({
			'added':True,
			'song':songId
			})

def getSong(request):
	received_json_data = json.loads(request.GET['request'])
	songId = received_json_data['id']
	if (Song.objects.filter(songId = songId).exists()):
		song = Song.objects.get(songId = songId)
		return JsonResponse({
			'info' : 'song', 
			'id' : song.songId,
			'artist' : song.artist, 
			'title' : song.title, 
			'duration' : song.duration, 
			'spotifyRef' : song.spotifyRef
			})
	else:
		return JsonResponse({
			'error':'song does not exist',
			'id' : songId
			})

def createRoom(request):
	received_json_data = json.loads(request.POST['request'])
	name = received_json_data['name']
	roomId = received_json_data['id']
	if (Room.objects.filter(name = name).exists()):
		return JsonResponse({
			'error':'room already exists',
			'id' : roomId
			})
	else:
		#remember to add the room to the creaters list of rooms
		creator = received_json_data['creator']
		newRoom = Room.objects.create(
			name = name,
			creator = creator,
			password = password
			)
		Member.objects.create(
			user = creator,
			room = newRoom.id
			)
		return JsonResponse({
			'added':True,
			'room' : newRoom.id
			})

def getRoom(request):
	received_json_data = json.loads(request.GET['request'])
	roomId = received_json_data['id']
	if (Room.objects.filter(roomId = roomId).exists()):
		room = Room.objects.get(roomId = roomId)
		playlist = Member.objects.filter(room = roomId)
		addedBy = Member.objects.filter(room = roomId)
		members = Member.objects.filter(room = roomId)
		return JsonResponse({
			'info' : 'room',
			'id' : room.roomId,
			'creator' : room.creator,
			'name' : room.name,
			'password' : room.password,
			'playlist' : playlist,
			'addedBy' : addedBy,
			'members' : members
			})
	else:
		return JsonResponse({
			'error':'room does not exist',
			'id' : roomId
			})

def joinRoom(request):
	#also have to write code to deal with a possible password
	#should change format of JSON10 to deal with this
	received_json_data = json.loads(request.POST['request'])
	roomName = received_json_data['name']
	if (Room.objects.filter(roomName = roomName).exists()):
		#add user to the room's members list
		#add room to user's rooms
		#other than that, same code as getRoom basically
		return JsonResponse({
			'name' : room.name
			})