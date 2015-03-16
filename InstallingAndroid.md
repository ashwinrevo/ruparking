Instructions to install Android on your computer

# Introduction #

Here is a list of steps to be followed to install the android sdk. You can find the original document here:

http://developer.android.com/sdk/1.6_r1/installing.html


# Details #

**Step 1: Installing a Java Development Kit (JDK)**

Download JDK 6 Update from here:
http://java.sun.com/javase/downloads/index.jsp

There are a few versions of JDK 6 available. Download anyone. I downloaded JDK 6 Update 16.

After downloading, follow installation instructions.

**Step 2: Installing Eclipse**

Download the eclipse installer from here:
http://www.eclipse.org/downloads/

Again there are many versions. I chose the last one Eclipse classic 3.5.1 and its working fine.

**Step 3: Downloading Android**

Download Android from here:
http://developer.android.com/sdk/1.6_r1/index.html

Choose the windows or mac version. Dont install the eclipse plugin.
You only need to unzip the contents and save it in your system. Remember the location of the folder where you are saving the contents.

**Step 4: Installing Android plugin in eclipse**

Start Eclipse, then select Help > Install New Softare.
In the Available Software dialog, click Add..
In the Add Site dialog that appears, enter a name for the remote site (e.g., "Android Plugin") in the "Name" field.

In the "Location" field, enter this URL:

https://dl-ssl.google.com/android/eclipse/

Note: If you have trouble aqcuiring the plugin, you can try using "http" in the URL, instead of "https" (https is preferred for security reasons).
Click OK.

Back in the Available Software view, you should now see "Developer Tools" added to the list. Select the checkbox next to Developer Tools, which will automatically select the nested tools Android DDMS and Android Development Tools. Click Next.

In the resulting Install Details dialog, the Android DDMS and Android Development Tools features are listed. Click Next to read and accept the license agreement and install any dependencies, then click Finish.

Restart Eclipse.

Start eclipse again
Select Window > Preferences... to open the Preferences panel
Select Android from the left panel
For the SDK Location in the main panel, click Browse... and locate your downloaded SDK directory.
Click Apply, then OK.