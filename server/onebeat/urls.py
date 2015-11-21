from django.conf.urls import url
from . import views

urlpatterns = [
    url(r'^$', views.redirect, name='redirect'),

    url(r'^existUser', views.existUser, name='existUser'),
    url(r'^addUser', views.addUser, name='addUser'),
    url(r'^getUser', views.getUser, name='getUser'),
    url(r'^existSong', views.existSong, name='existSong'),
    url(r'^addSong', views.addSong, name='addSong'),
    url(r'^getSong', views.getSong, name='getSong'),
]