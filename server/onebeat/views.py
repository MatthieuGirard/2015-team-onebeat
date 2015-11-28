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
			'added' : False,
			'error' : 'user already exists',
			'id' : userId
			})
	else:
		User.objects.create(
			userId = userId,
			name = name
			)
		return JsonResponse({
			'added' : True,
			'user' : userId
			})

def getUser(request):
	received_json_data = json.loads(request.GET['request'])
	userId = received_json_data['id']
	if (User.objects.filter(userId = userId).exists()):
		user = User.objects.get(userId = userId)
		#we need to make sure rooms.room only contains the room id and not the user id
		rooms = Member.objects.filter(user = userId)
		return JsonResponse({
			'info' : 'user',
			'id' : userId,
			'name' : user.name,
			'rooms' : rooms.room
			})
	else:
		return JsonResponse({
			'added' : False,
			'error' : 'user does not exist',
			'id' : userId
			})

def addSong(request):
	received_json_data = json.loads(request.POST['request'])
	songId = received_json_data['id']
	#if we don't have the song, we add it to our database
	if ( not(Song.objects.filter(songId = songId).exists()) ):
		title = received_json_data['title']
		artist = received_json_data['artist']
		duration = received_json_data['duration']
		spotifyRef = received_json_data['spotifyRef']
		Song.objects.create(
			songId = songId,
			artist = artist,
			title = title,
			duration = duration,
			spotifyRef = spotifyRef,
			)
	addedBy = received_json_data['addedBy']
	room = received_json_data['room']
	Playlist.objects.create(
		room = room,
		song = songId,
		addedBy = addedBy
		)
	return JsonResponse({
		'added' : True,
		'song' : songId
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
			'added' : False,
			'error' : 'song does not exist',
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
		#also, same as in getUser, find how to get different arrays for songs and addedBy from playlist
		playlist = Playlist.objects.filter(room = roomId)
		members = Member.objects.filter(room = roomId)
		return JsonResponse({
			'info' : 'room',
			'id' : room.id,
			'creator' : room.creator,
			'name' : room.name,
			'password' : room.password,
			'playlist' : playlist.song,
			'addedBy' : playlist.addedBy,
			'members' : members.user
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
	userId = received_json_data['user']
	if (Room.objects.filter(roomName = roomName).exists()):
		room = Room.objects.get(roomName = roomName)
		Member.objects.create(
			user = userId,
			room = room.id
			)
		return JsonResponse({
			'added' : True,
			'roomId' : room.id,
			'userId' : userId,
			})
	else:
		return JsonResponse({
			'added' : False,
			'error' : 'room does not exist',
			'room' : roomName
			})