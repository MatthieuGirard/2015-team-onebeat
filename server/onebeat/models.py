from django.db import models

class User(models.Model):
	userId = models.IntegerField()
	userId.primary_key = True
	name = models.CharField(max_length=100)

	def __str__(self):
		return self.name

class Song(models.Model):
	songId = models.IntegerField()
	songId.primary_key = True
	artist = models.CharField(max_length=100)
	title = models.CharField(max_length=200)
	duration = models.CharField(max_length=20)
	spotifyRef = models.CharField(max_length=100)
	addedBy = models.ForeignKey(User)

	def __str__(self):
		return self.title

class Room(models.Model):
	roomId = models.IntegerField()
	roomId.primary_key = True
	creator = models.ForeignKey(User)
	name = models.CharField(max_length=100)

	def __str__(self):
		return self.name

class Playlist(models.Model):
	room = models.ForeignKey(Room)
	song = models.ForeignKey(Song)
	addedBy = models.ForeignKey(User)

class Member(models.Model):
	user = models.ForeignKey(User)
	room = models.ForeignKey(Room)