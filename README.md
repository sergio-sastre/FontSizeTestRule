# FontSizeRule
A Junit4 TestRule to be used together with its `org.junit.runners.Parameterized`. It simulates changing the font size on a device/emulator, as it would be done by going to "Settings > display > Font size"

This helps to write snapshot tests that can catch visual regresion bugs like this one

| **FONT SIZE NORMAL**   |      **FONT SIZE HUGE**      |
|----------|:-------------:|
| <img src="https://user-images.githubusercontent.com/6097181/129961748-5daa42a2-8801-4b26-832a-d4191e205bc9.png" width="450"> |  <img src="https://user-images.githubusercontent.com/6097181/129962082-f2ff110f-6500-4a02-8765-7f70a7b8ee61.png" width="450"> |


You can read more about it in my series on [snapshot testing](https://sergiosastre.hashnode.dev/an-introduction-to-snapshot-testing-on-android-in-2021), featured in [AndroidWeekly #479](https://androidweekly.net/issues/issue-479) and [Software Testing Notes #20](https://softwaretestingnotes.substack.com/p/issue-20-software-testing-notes)

Code samples coming very soon...
