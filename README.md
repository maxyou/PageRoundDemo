# PageRoundDemo

I don't like the mode of swiping to the bottom to load more, as it doesn't quickly jump to a specific location.

Also, the paging 2 or 3 library that Jetpack offered is not easy to use. It is too deeply bound to ViewModel or Repository structure. It is difficult to freely change the structure later on.

I like to select pages directly. It's sample and clear. I found that there is very little such code on Github, so I extracted the relevant code from my own project, maybe someone needs this reference.

You can copy the PageRound.kt file directly to your project directory. Currently there is only Compose version.

You need to define your favorite button and spacer pattern, and then pass to the PageRound(), very simple. App.kt is an example.


https://user-images.githubusercontent.com/1485628/150663387-610d171a-c46d-4af2-b316-81403200354f.mp4


# License
MIT
