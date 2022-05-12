# PageRoundDemo

I don't like the mode of swiping to the bottom to load more, as it doesn't quickly jump to a specific location.

Also, the paging 2 or 3 library that Jetpack offered is not easy to use. It is too deeply bound to ViewModel or Repository structure. It is difficult to freely change the structure later on.

I like to select pages directly. It's simple and clear. I found that there is very little such code on Github, so I extracted the relevant code from my own project, maybe someone needs this reference.

You can copy the PageRound.kt file directly to your project directory. Currently there is only Compose version.

You need to define your favorite button and spacer pattern, and then pass to the PageRound(), very simple. App.kt is an example.


https://user-images.githubusercontent.com/1485628/150663387-610d171a-c46d-4af2-b316-81403200354f.mp4


# Usage

![photo_2022-01-23_17-00-01](https://user-images.githubusercontent.com/1485628/150672152-157283d0-c873-466e-8dae-4e3674a0d9d5.jpg)

Above shows one RoundInterval is between the RoundButton 1 and 4, another is between RoundButton 8 and 30.

First, define your RoundInterval and RoundButton:

    val RoundInterval = @Composable {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(2.dp)
                .background(Color(0x22AC9C95)),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "---")
        }
    }

    val RoundButton = @Composable { count: Int, isCurrent: Boolean, onClick: (Int) -> Unit ->
        Box(
            modifier = Modifier
                .padding(2.dp)
                .aspectRatio(1f)
                .clip(CircleShape)
                .fillMaxSize()
                .clickable { onClick(count) }
                .background(if (isCurrent) Color(0xFFACDC75) else Color(0xFF6C9C35)),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "${count}")
        }
    }

And then, put it into PageRound()

    Box(modifier = Modifier
            .fillMaxWidth(1f)
            .height(40.dp)
            .background(Color(0xffccccff)),
            contentAlignment = Alignment.Center
        ) {
            PageRound(
                current = current,
                ext = ext,
                totalDocs = totalDocs,
                pageSize = pageSize,
                onClick = { current = it },
                RoundButton = RoundButton,
                RoundInterval = RoundInterval
            )
        }

Done!

# License
MIT
