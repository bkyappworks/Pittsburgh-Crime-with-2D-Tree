# Pittsburgh-Crime-with-2D-Tree
The primary input to this application is a comma delimited text file containing crime records from the Pittsburgh area. These data were compiled during the 1990’s (an exceptionally high crime decade in Pittsburgh). The file is named CrimeLatLonXY.csv and is found on the course schedule.

For each crime record, the file contains (X, Y) coordinate pairs in the state plane coordinate system. These (X, Y) coordinates are useful for calculating the distance between points (using the Pythagorean theorem). Each record also contains latitude and longitude coordinates. These coordinates are useful for displaying locations in GIS tools such as Google Earth. Aall of the data contained in the file will be represented in the tree.
The data structure implemented is a type of space- partitioning tree called a 2d Tree. This data structure stores the crime records – one crime record per node in the tree. 

When the main method in TwoDTreeDriver.java is run it will load the crime data file into the 2d tree. It will interact with the user as requested.

Crime file loaded into 2d tree with 27218 records.
What would you like to do?<br />
1: inorder<br />
2: preorder<br />
3: levelorder<br />
4: postorder<br />
5: reverseLevelOrder<br />
6: search for points within rectangle<br />
7: search for nearest neighbor<br />
8: quit<br />

![image](https://github.com/bkyappworks/Pittsburgh-Crime-with-2D-Tree/blob/main/rectangle-search-result.png)