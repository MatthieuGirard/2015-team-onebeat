import json
from django.shortcuts import render
from django.shortcuts import redirect
from django.http import JsonResponse
from .models import User
from .models import Song
from .models import Room
from .models import Playlist
from .models import Member

def getRoom2(request):
	roomId = request.GET['id']
	
	if (Room.objects.filter(id = roomId).exists()):
		room = Room.objects.get(id = roomId)
		playlist = Playlist.objects.filter(room = room)
		members = Member.objects.filter(room = room).values('user')
		songsId = [d['song'] for d in playlist]
		song = Song.objects.get(id = songId)
		
		return JsonResponse({
			'info' : 'room',
			'id' : room.id,
			'creator' : room.creator,
			'name' : room.name,
			'password' : room.password,
			'songs' : [ { 
				'artist' : song.artist,
				'title' : song.title,
				'duration' : song.duration,
				'spotifyRef' : song.spotifyRef
			} for songId in songsId],
			'members' : [d['user'] for d in users]
			})
	
	else:
		return JsonResponse({
			'error':'room does not exist',
			'id' : roomId
			})