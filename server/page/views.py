from django.shortcuts import render
from django.http import JsonResponse
from django.views.decorators.csrf import csrf_exempt

# Create your views here.

def post_list(request):
    return render(request, 'page/post_list.html', {})

@csrf_exempt
def ret_value(request):
	return JsonResponse({'foo':'bar'})
