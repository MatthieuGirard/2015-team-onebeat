from django.conf.urls import url
from . import views

urlpatterns = [
    # url(r'^$', views.post_list, name='post_list'),
    url(r'^existUser', views.existUser, name='existUser'),
    url(r'^addUser', views.addUser, name='addUser'),
    url(r'^getUser', views.getUser, name='getUser'),
]