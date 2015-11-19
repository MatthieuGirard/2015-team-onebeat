import json
from django.shortcuts import render
from django.shortcuts import redirect
from django.http import JsonResponse
from .models import User
from .models import Song
from .models import Room

# Create your views here.
def redirect(request):
	if (request.method == "GET"):
		received_json_data=json.loads(request.GET['request'])
	elif (request.method == "POST"):
		received_json_data=json.loads(request.POST['request'])
	req = received_json_data['request']
	
	if (req == "getUser"):
		redirect('/getUser')
		
def existUser(request):
	received_json_data=json.loads(request.GET['request'])
	userId=received_json_data['id']
	if (User.objects.filter(userId=userId).exists()):
		return JsonResponse({'existUser':'true'})
	else:
		return JsonResponse({'existUser':'false'})

def addUser(request):
	received_json_data=json.loads(request.POST['request'])
	userId=received_json_data['id']
	name=received_json_data['name']
	User.objects.create(userId=userId,name=name)
	return JsonResponse({'added':'true', 'user':name})

def getUser(request):
	received_json_data=json.loads(request.GET['request'])
	userId=received_json_data['id']
	user = User.objects.get(userId=userId)
	return JsonResponse({'info': 'user', 'id':userId, 'pseudo':user.name})

def addSong(request):
	received_json_data=json.loads(request.POST['request'])
	songId=received_json_data['id']
	title=received_json_data['title']
	if (not Song.objects.filter(songId=songId).exists()):
		artist=received_json_data['artist']
		duration=received_json_data['duration']
		spotifyRef=received_json_data['spotifyRef']
		addedBy=received_json_data['addedBy']
		Song.objects.create(songId=songId, artist=artist, title=title, duration=duration, spotifyRef=spotifyRef,addedBy=addedBy)
	return JsonResponse({'added':'true', 'song':title})

def getSong(request):
	received_json_data=json.loads(request.GET['request'])
	songId = received_json_data['id']
	song = Song.objects.get(songId=songId)
	return JsonResponse({'info' : 'song', 
		'id' : song.songId,
		'artist' : song.artist, 
		'title' : song.title, 
		'duration' : song.duration, 
		'spotifyRef' : song.spotifyRef,
		'addedBy' : song.addedBy})