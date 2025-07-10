interface Button {
    void paint();
}

interface TextBox {
    void render();
}

class WindowsButton implements Button {
    public void paint() {
        System.out.println("Rendering Windows Button");
    }
}

class MacOSButton implements Button {
    public void paint() {
        System.out.println("Rendering MacOS Button");
    }
}

class WindowsTextBox implements TextBox {
    public void render() {
        System.out.println("Rendering Windows TextBox");
    }
}

class MacOSTextBox implements TextBox {
    public void render() {
        System.out.println("Rendering MacOS TextBox");
    }
}

interface GUIFactory {
    Button createButton();
    TextBox createTextBox();
}

class WindowsFactory implements GUIFactory {
    public Button createButton() {
        return new WindowsButton();
    }
    public TextBox createTextBox() {
        return new WindowsTextBox();
    }
}

class MacOSFactory implements GUIFactory {
    public Button createButton() {
        return new MacOSButton();
    }
    public TextBox createTextBox() {
        return new MacOSTextBox();
    }
}
