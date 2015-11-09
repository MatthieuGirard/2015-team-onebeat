from django.shortcuts import render
from django.http import JsonResponse
from .models import Users

# Create your views here.
def existUser(request):
	
	return JsonResponse({'bool':'true'})