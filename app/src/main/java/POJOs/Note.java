package POJOs;

public class Note {

    private String id;
    private String Note;
    private boolean checked;

    public Note() {
    }

    public Note(String id, String note) {
        this.id = id;
        Note = note;
        checked = false;
    }
    public Note(String id, String note, boolean checked) {
        this.id = id;
        Note = note;
        this.checked = checked;
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

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
