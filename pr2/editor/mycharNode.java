package editor;
import javafx.scene.text.Text;
public class mycharNode {
    private mycharNode next;
    private mycharNode prev;
    private Text value = null;
    public mycharNode(mycharNode p, Text t, mycharNode n){
        this.prev = p;
        this.value = t;
        this.next = n;
    }
    public mycharNode getPrev(){
        return this.prev;
    }
    public mycharNode getNext(){
        return this.next;
    }

    public void setPrev(mycharNode prev){
        this.prev = prev;
    }
    public void setNext(mycharNode next){
        this.next = next;
    }
    public Text getVal(){
        return this.value;
    }
    public void setVal(Text c){
        this.value = c;
    }





}
