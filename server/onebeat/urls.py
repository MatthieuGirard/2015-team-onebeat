from django.conf.urls import url
from . import views

urlpatterns = [
    #url(r'^$', views.redirect, name='redirect'),

    url(r'^getUser', views.getUser, name='getUser'),
    url(r'^addUser', views.addUser, name='addUser'),
    url(r'^getSong', views.getSong, name='getSong'),
    url(r'^addSong', views.addSong, name='addSong'),
    url(r'^getRoom', views.addSong, name='getRoom'),
    url(r'^createRoom', views.addSong, name='addRoom'),
]