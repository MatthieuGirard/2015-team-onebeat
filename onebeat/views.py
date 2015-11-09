import json
from django.shortcuts import render
from django.http import JsonResponse
from .models import User

# Create your views here.
def existUser(request):
	return JsonResponse({'bool':'true'})

def addUser(request):
	#received_json_data=json.loads(request.body.decode('utf-8'))
	userId=request.POST.get('userId',None)
	name=request.POST.get('name',None)
	User.objects.create(userId=userId,name=name)
	return JsonResponse({'added':'true', 'user':name})