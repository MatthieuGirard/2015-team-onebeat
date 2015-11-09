from django.shortcuts import render
from django.http import JsonResponse
from .models import User

# Create your views here.
def existUser(request):
	return JsonResponse({'bool':'true'})

def addUser(request):
	User.objects.create(userId=12345,name='test')
	return JsonResponse({'added':'true', 'user':'test'})