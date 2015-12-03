def getRoom2(request):
	roomId = request.GET['id']
	
	if (Room.objects.filter(roomId = roomId).exists()):
		room = Room.objects.get(roomId = roomId)
		playlist = Playlist.objects.filter(room = roomId)
		members = Member.objects.filter(room = roomId).values('user')
		songsId = [d['song'] for d in playlist]
		
		return JsonResponse({
			'info' : 'room',
			'id' : room.id,
			'creator' : room.creator,
			'name' : room.name,
			'password' : room.password,
			'songs' : [ { 
				'artist' : Song.objects.get(id = songId).artist
				'title' : Song.objects.get(id = songId).title
				'duration' : Song.objects.get(id = songId).duration
				'spotifyRef' : Song.objects.get(id = songId).spotifyRef
			} songId in songsId],
			'members' : [d['user'] for d in users]
			})
	
	else:
		return JsonResponse({
			'error':'room does not exist',
			'id' : roomId
			})