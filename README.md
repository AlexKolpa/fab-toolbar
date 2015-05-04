Floating Action Button Toolbar
==============================

[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-fab--toolbar-brightgreen.svg?style=flat)](http://android-arsenal.com/details/1/1740)

Provides the Floating Action Button Toolbar [as specified in the Material Design Guide](http://www.google.com/design/spec/components/buttons-floating-action-button.html#buttons-floating-action-button-transitions) in a simple library.

![demo](https://cloud.githubusercontent.com/assets/3596362/7158276/a4883268-e378-11e4-9918-78d071ac7a97.gif)

Usage
====

```xml
<com.github.alexkolpa.fabtoolbar.FabToolbar
	android:id="@+id/fab_toolbar"
	android:layout_width="match_parent"
	android:layout_height="wrap_content"
	android:layout_gravity="bottom"
	tb:tb_animation_duration="500"
	tb:tb_button_gravity="end"
	tb:tb_container_gravity="center"
	>

	<ImageView
		android:id="@+id/attach"
		android:layout_width="wrap_content"
		android:layout_height="wrap_content"
		android:src="@drawable/ic_attachment_white_48dp"
		android:layout_marginLeft="@dimen/icon_margin"
		android:layout_marginRight="@dimen/icon_margin"
		/>

	<!-- More buttons can be added here -->

</com.github.alexkolpa.fabtoolbar.FabToolbar>
```

To hide or show the Toolbar, simply call `hide()` and `show()`.

```java
FabToolbar fabToolbar = ((FabToolbar) findViewById(R.id.fab_toolbar));

findViewById(R.id.attach).setOnClickListener(new View.OnClickListener() {
	public void onClick(View v) {
		fabToolbar.hide();
	}
});

```

Adding dependencies
=====================

Gradle:
```groovy
dependencies {
	compile 'com.github.alexkolpa:floating-action-button-toolbar:0.5.1'
}
```

Maven:
```xml
<dependency>
	<groupId>com.github.alexkolpa</groupId>
	<artifactId>floating-action-button-toolbar</artifactId>
	<version>0.5.1</version>
</dependency>
```

License
--------

    The MIT License (MIT)

    Copyright (c) 2015 Alex Kolpa
    
    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:
    
    The above copyright notice and this permission notice shall be included in
    all copies or substantial portions of the Software.
    
    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
    THE SOFTWARE.
