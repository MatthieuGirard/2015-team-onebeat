from django.db import models

class Users(models.Model):
	userId = models.IntegerField()
	userId.primary_key = True
	name = models.CharField(max_length=50)

	def __str__(self):
		return self.name