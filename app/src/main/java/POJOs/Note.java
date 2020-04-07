package POJOs;

public class Note {

    private String relatedTripId;
    private String Note;
    private String NoteId;
    private boolean checked;


    public Note() {
    }

    public Note(String relatedTripId, String note) {
        this.relatedTripId = relatedTripId;
        Note = note;
        checked = false;
    }
    public Note(String relatedTripId, String note, boolean checked) {
        this.relatedTripId = relatedTripId;
        Note = note;
        this.checked = checked;
    }

    public String getNoteId() {
        return NoteId;
    }

    public void setNoteId(String noteId) {
        NoteId = noteId;
    }

    public String getRelatedTripId() {
        return relatedTripId;
    }

    public String getNote() {
        return Note;
    }

    public void setRelatedTripId(String relatedTripId) {
        this.relatedTripId = relatedTripId;
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
