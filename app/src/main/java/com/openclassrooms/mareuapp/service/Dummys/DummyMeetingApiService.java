package com.openclassrooms.mareuapp.service.Dummys;

import com.openclassrooms.mareuapp.model.Meeting;
import com.openclassrooms.mareuapp.service.ApiServices.MeetingApiService;
import com.openclassrooms.mareuapp.service.Generators.MeetingApiServiceGenerator;

import java.util.List;


public class DummyMeetingApiService implements MeetingApiService {

    private List<Meeting> meetings = MeetingApiServiceGenerator.generateMeetings();


    /**
     * Get Meeting's List
     */

    public List<Meeting> getMeetings() {
        return meetings;
    }

    /**
     * Deletes a Meeting from List
     *
     * @param meeting
     */

    public void deleteMeeting(Meeting meeting) {
        meetings.remove(meeting);
    }

    /**
     * Creates a Meeting and add it to List
     *
     * @param meeting
     */

    public void createMeeting(Meeting meeting) {
        meetings.add(meeting);
    }
}


/**
public static List<Meeting> getMeetingsMatchDate(Calendar date, List<Meeting> meetings) {
    List<Meeting> tmp = new ArrayList<>();

    for (Meeting m : meetings)
        if (sameDate(m.getDate(), date))
            tmp.add(m);

    return tmp;
}


 * Return ordered meetings list filter by room name
 * @param roomName selected room name
 * @param meetings meetings list to filter
 * @return filtered meetings list

public static List<Meeting> getMeetingsMatchRoomName(String roomName, List<Meeting> meetings) {
List<Meeting> tmp = new ArrayList<>();

for (Meeting m: meetings)
if (m.getName().trim().equals(roomName.trim()))
tmp.add(m);

return tmp;
}


public List<Meeting> updatelist(Calendar date, String roomName) {
if(date != null && roomName != null)
return getMeetingsMatchDate(date, getMeetingsMatchRoomName(roomName, meetings));
else if(date != null)
return getMeetingsMatchDate(date, meetings);
else if(roomName != null && ! roomName.isEmpty())
return getMeetingsMatchRoomName(roomName, meetings);
return meetings;
}
 */
