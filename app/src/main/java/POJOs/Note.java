package POJOs;

public class Note {

    private String id;
    private String Note;

    public Note() {
    }

    public Note(String id, String note) {
        this.id = id;
        Note = note;
    }

    public String getId() {
        return id;
    }

    public String getNote() {
        return Note;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNote(String note) {
        Note = note;
    }
}
