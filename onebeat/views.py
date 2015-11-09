import json
from django.shortcuts import render
from django.http import JsonResponse
from .models import User

# Create your views here.
def existUser(request):
	received_json_data=json.loads(request.GET['request'])
	userId=received_json_data['userId']
	if (User.objects.filter(userId=userId).exists()):
		return JsonResponse({'bool':'true'})
	else:
		return JsonResponse({'bool':'false'})

def addUser(request):
	received_json_data=json.loads(request.POST['request'])
	userId=received_json_data['userId']
	name=received_json_data['name']
	User.objects.create(userId=userId,name=name)
	return JsonResponse({'added':'true', 'user':name})

def getUser(request):
	received_json_data=json.loads(request.GET['request'])
	userId=received_json_data['userId']
	user = User.objects.filter(userId=userId)
	return JsonResponse({'userId':userId, 'user':user.values('name')})