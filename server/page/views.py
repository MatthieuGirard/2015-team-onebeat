from django.shortcuts import render
from django.http import JsonResponse

# Create your views here.

def post_list(request):
    return render(request, 'page/post_list.html', {})

def ret_value(request):
	return JsonResponse({'foo':'bar'})
