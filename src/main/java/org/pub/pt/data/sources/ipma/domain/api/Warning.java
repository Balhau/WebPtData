package org.pub.pt.data.sources.ipma.domain.api;

public class Warning {
    private final String awarenessTypeName;
    private final String text;
    private final String idAreaAviso;
    private final String startTime;
    private final String awarenessLevelID;
    private final String endTime;

    public Warning(String awarenessTypeName, String text, String idAreaAviso, String startTime, String awarenessLevelID, String endTime) {
        this.awarenessTypeName = awarenessTypeName;
        this.text = text;
        this.idAreaAviso = idAreaAviso;
        this.startTime = startTime;
        this.awarenessLevelID = awarenessLevelID;
        this.endTime = endTime;
    }

    public String getAwarenessTypeName() {
        return awarenessTypeName;
    }

    public String getText() {
        return text;
    }

    public String getIdAreaAviso() {
        return idAreaAviso;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getAwarenessLevelID() {
        return awarenessLevelID;
    }

    public String getEndTime() {
        return endTime;
    }
}
