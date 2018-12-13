# Example Java Code

## Creating a 640x480 window
```
public WindowClass(){
  JFrame window = new JFrame("Title");
  window.setSize(640,480);
  window.setLocationRelativeTo(null);//centers window on screen
  window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//closes application when closed
  //Add UI elements, e.g. buttons, panels(containers), text boxes, etc.
  
  //DO THIS LAST
  window.setVisible(true);//Makes window visible to user
}
```

## Adding a component to a container
```
//In place where you create element:
JPanel panel = new JPanel();//The container we will be adding to
JButton btn = new JButton("A Button that does nothing");//create a button
panel.add(btn);
```

## Changing panel layout
```
//Method 1:
JPanel panel = new JPanel(new GridLayout(3,3));//creates a 3x3 grid, components are added left to right, 
//top to bottom. Padding can be down with empty containers
//Method 2:
panel.setLayout(new GridLayout(2,1));//Creates a 2x1 grid, and acts like 3x3.
/*
NOTE: GridLayout will automatically change to fit amount of components in the container.
*/
```
