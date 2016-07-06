package editor;
/**
 * Created by ranko on 29/06/16.
 */import javafx.scene.text.Text;
public class mytext {
    protected final mycharNode sentinel;
    protected mycharNode cursor;
    private int size;
    public mytext(){
        sentinel = new mycharNode(null, new Text() ,null);
        sentinel.setPrev(sentinel);
        sentinel.setNext(sentinel);
        cursor = sentinel;
        size = 0;
    }
    public void clear(){
        cursor = sentinel;
        sentinel.setNext(sentinel);
        sentinel.setPrev(sentinel);
        size = 0;
    }
    public Text[] getValues(){
        Text[] out = new Text[size];
        mycharNode beginning = sentinel.getNext();
        for (int i = 0; i < size; i++) {
            out[i] = beginning.getVal();
            beginning = beginning.getNext();
        }
        return out;
    }
    public void insertValue(Text c){
        mycharNode newnode = new mycharNode(cursor.getPrev(), c, cursor);
        cursor.setPrev(newnode);
        cursor.getPrev().setNext(newnode);
        size += 1;
    }
    public void insertValue(mycharNode c){
        if (c == sentinel || c == cursor){
            return;
        }
        c.setPrev(cursor.getPrev());
        c.setNext(cursor);
        cursor.setPrev(c);
        cursor.getPrev().setNext(c);
        size += 1;
    }
    public void insertValueEnd(Text c){
        mycharNode newnode = new mycharNode(sentinel.getPrev(), c, sentinel);
        sentinel.getPrev().setNext(newnode);
        sentinel.setPrev(newnode);
        size += 1;
    }
    protected mycharNode popNodeFromStart(){
        mycharNode out = sentinel.getNext();
        if (sentinel.getNext() == cursor){
            cursor = sentinel.getNext().getNext();
        }
        sentinel.setNext(sentinel.getNext().getNext());
        sentinel.getNext().setPrev(sentinel);
        if (size != 0){
            size -= 1;
        }
        return out;
    }
    protected  mycharNode popNodeFromEnd(){
        mycharNode out = sentinel.getPrev();
        if (sentinel.getPrev() == cursor){
            cursor = sentinel;
        }
        sentinel.setPrev(sentinel.getPrev().getPrev());
        sentinel.getPrev().setNext(sentinel);
        if (size != 0) {
            size -= 1;
        }
        return out;

    }
    //pops from current cursor pos
    protected mycharNode popFromPosition(){
        mycharNode out;// = cursor;
        if(isAtEnd()) {
            out = cursor.getPrev();
            cursor.getPrev().getPrev().setNext(cursor);
            cursor.setPrev(cursor.getPrev().getPrev());
        }
        else {
            out = cursor;
            cursor.getPrev().setNext(cursor.getNext());
            cursor.getNext().setPrev(cursor.getPrev());
            if (!isAtStart()){
                cursor = cursor.getPrev();
            }
            else {
                cursor = cursor.getNext();
            }
        }
        if (size != 0){
            size -= 1;
        }
        return out;

    }
    public String getString(){
        return sentinel.getNext().getVal().getText();

    }
    public int size(){
        return size;
    }
    public boolean isAtStart(){
        return (cursor.getPrev() == sentinel);
    }
    public boolean isAtEnd(){
        return (cursor == sentinel);
    }




}
