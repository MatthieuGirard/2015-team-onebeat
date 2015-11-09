from django.shortcuts import render
from django.http import JsonResponse
from .models import User

# Create your views here.
def default(request):


def existUser(request):
	return JsonResponse({'bool':'true'})

def addUser(request):
	received_json_data=json.loads(request.body)
	userId=received_json_data['userId']
	name=received_json_data['name']
	User.objects.create(userId=userId,name=name)
	return JsonResponse({'added':'true', 'user':name})

def getUser(request):