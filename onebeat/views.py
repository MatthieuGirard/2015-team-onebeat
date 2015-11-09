import json
from django.shortcuts import render
from django.http import JsonResponse
from .models import User

# Create your views here.
def existUser(request):
	userId=request.GET['userId']
	if (User.objects.filter(userId=userId).exists()):
		return JsonResponse({'bool':'true'})
	else:
		return JsonResponse({'bool':'false'})

def addUser(request):
	received_json_data=json.loads(request.POST['request'])
	userId=received_json_data['userId']
	ame=received_json_data['name']
	#userId=request.POST.get('userId',None)
	#name=request.POST.get('name',None)
	User.objects.create(userId=userId,name=name)
	return JsonResponse({'added':'true', 'user':name})