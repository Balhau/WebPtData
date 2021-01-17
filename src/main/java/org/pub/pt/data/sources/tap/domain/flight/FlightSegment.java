package org.pub.pt.data.sources.tap.domain.flight;

import java.util.List;

public class FlightSegment {
    private final int Number;
    private final String CarrierIconClass;
    private final String CarrierIconRedesignClass;
    private final String CarrierCode;
    private final String FlightNumber;
    private final boolean ShowAirlineName;
    private final String AirlineName;
    private final String OperatingAirlineCode;
    private final boolean ShowAircraftOwner;
    private final String OwnerAirlineName;
    private final List<String> IntermediateStopLocations;
    private final boolean IsLongLayover;
    private final boolean IsNotLastSegment;
    private final String LayoverTimeFormated;
    private final boolean IsLongHaul;
    private final boolean ShouldShowLongLayoverNotice;
    private final boolean IsNeoFlight;
    private final boolean IsGovernmentApproval;
    private final String GovernmentApprovalText;
    private final String GuaranteesText;
    private final String GovernmentLinkText;
    private final boolean IsAirportChange;
    private final boolean IsDefinedGroundTime;
    private final String GroundTime;
    private final String DepDate;
    private final String DepartureDate;
    private final String DepartureTime;
    private final String ArrDate;
    private final String ArrivalTime;
    private final String FlightString;
    private final boolean IsIntermediateStop;
    private final String Origin;
    private final String Destination;
    private final String OriginTLC;
    private final String DestinationTLC;
    private final String OriginCountryCode;
    private final String OriginCountry;
    private final String OriginContinetCode;
    private final String DestinationCountryCode;
    private final String DestinationCountry;
    private final String DestinationContinentCode;
    private final String FlightTimeFormated;
    private final String EquipmentCode;
    private final int SalesFlightType;
    private final boolean IsBusOperated;
    private final boolean IsSegmentInfo;
    private final String SegmentInfoHtml;
    private final String SegmentInfoHtmlRedesign;
    private final boolean IsCodeShare;
    private final String Equipment;
    private final boolean ShouldShowDayDifference;
    private final int DayDifference;
    //private final Object MilesAccrualDictionary;
    private final boolean IsIntercontinental;
    private final String BookingClassCode;
    private final String Aircraft;
    private final String DestinationTerminal;
    private final String OriginTerminal;
    private final List<String> IntermediateStops;

    public FlightSegment(int number, String carrierIconClass, String carrierIconRedesignClass, String carrierCode, String flightNumber, boolean showAirlineName,
                         String airlineName, String operatingAirlineCode, boolean showAircraftOwner, String ownerAirlineName, List<String> intermediateStopLocations,
                         boolean isLongLayover, boolean isNotLastSegment, String layoverTimeFormated, boolean isLongHaul, boolean shouldShowLongLayoverNotice, boolean isNeoFlight,
                         boolean isGovernmentApproval, String governmentApprovalText, String guaranteesText, String governmentLinkText, boolean isAirportChange, boolean isDefinedGroundTime,
                         String groundTime, String depDate, String departureDate, String departureTime, String arrDate, String arrivalTime, String flightString, boolean isIntermediateStop,
                         String origin, String destination, String originTLC, String destinationTLC, String originCountryCode, String originCountry, String originContinetCode,
                         String destinationCountryCode, String destinationCountry, String destinationContinentCode, String flightTimeFormated, String equipmentCode, int salesFlightType,
                         boolean isBusOperated, boolean isSegmentInfo, String segmentInfoHtml, String segmentInfoHtmlRedesign, boolean isCodeShare, String equipment, boolean shouldShowDayDifference,
                         int dayDifference, boolean isIntercontinental, String bookingClassCode, String aircraft, String destinationTerminal, String originTerminal, List<String> intermediateStops) {
        Number = number;
        CarrierIconClass = carrierIconClass;
        CarrierIconRedesignClass = carrierIconRedesignClass;
        CarrierCode = carrierCode;
        FlightNumber = flightNumber;
        ShowAirlineName = showAirlineName;
        AirlineName = airlineName;
        OperatingAirlineCode = operatingAirlineCode;
        ShowAircraftOwner = showAircraftOwner;
        OwnerAirlineName = ownerAirlineName;
        IntermediateStopLocations = intermediateStopLocations;
        IsLongLayover = isLongLayover;
        IsNotLastSegment = isNotLastSegment;
        LayoverTimeFormated = layoverTimeFormated;
        IsLongHaul = isLongHaul;
        ShouldShowLongLayoverNotice = shouldShowLongLayoverNotice;
        IsNeoFlight = isNeoFlight;
        IsGovernmentApproval = isGovernmentApproval;
        GovernmentApprovalText = governmentApprovalText;
        GuaranteesText = guaranteesText;
        GovernmentLinkText = governmentLinkText;
        IsAirportChange = isAirportChange;
        IsDefinedGroundTime = isDefinedGroundTime;
        GroundTime = groundTime;
        DepDate = depDate;
        DepartureDate = departureDate;
        DepartureTime = departureTime;
        ArrDate = arrDate;
        ArrivalTime = arrivalTime;
        FlightString = flightString;
        IsIntermediateStop = isIntermediateStop;
        Origin = origin;
        Destination = destination;
        OriginTLC = originTLC;
        DestinationTLC = destinationTLC;
        OriginCountryCode = originCountryCode;
        OriginCountry = originCountry;
        OriginContinetCode = originContinetCode;
        DestinationCountryCode = destinationCountryCode;
        DestinationCountry = destinationCountry;
        DestinationContinentCode = destinationContinentCode;
        FlightTimeFormated = flightTimeFormated;
        EquipmentCode = equipmentCode;
        SalesFlightType = salesFlightType;
        IsBusOperated = isBusOperated;
        IsSegmentInfo = isSegmentInfo;
        SegmentInfoHtml = segmentInfoHtml;
        SegmentInfoHtmlRedesign = segmentInfoHtmlRedesign;
        IsCodeShare = isCodeShare;
        Equipment = equipment;
        ShouldShowDayDifference = shouldShowDayDifference;
        DayDifference = dayDifference;
        IsIntercontinental = isIntercontinental;
        BookingClassCode = bookingClassCode;
        Aircraft = aircraft;
        DestinationTerminal = destinationTerminal;
        OriginTerminal = originTerminal;
        IntermediateStops = intermediateStops;
    }

    public int getNumber() {
        return Number;
    }

    public String getCarrierIconClass() {
        return CarrierIconClass;
    }

    public String getCarrierIconRedesignClass() {
        return CarrierIconRedesignClass;
    }

    public String getCarrierCode() {
        return CarrierCode;
    }

    public String getFlightNumber() {
        return FlightNumber;
    }

    public boolean isShowAirlineName() {
        return ShowAirlineName;
    }

    public String getAirlineName() {
        return AirlineName;
    }

    public String getOperatingAirlineCode() {
        return OperatingAirlineCode;
    }

    public boolean isShowAircraftOwner() {
        return ShowAircraftOwner;
    }

    public String getOwnerAirlineName() {
        return OwnerAirlineName;
    }

    public List<String> getIntermediateStopLocations() {
        return IntermediateStopLocations;
    }

    public boolean isLongLayover() {
        return IsLongLayover;
    }

    public boolean isNotLastSegment() {
        return IsNotLastSegment;
    }

    public String getLayoverTimeFormated() {
        return LayoverTimeFormated;
    }

    public boolean isLongHaul() {
        return IsLongHaul;
    }

    public boolean isShouldShowLongLayoverNotice() {
        return ShouldShowLongLayoverNotice;
    }

    public boolean isNeoFlight() {
        return IsNeoFlight;
    }

    public boolean isGovernmentApproval() {
        return IsGovernmentApproval;
    }

    public String getGovernmentApprovalText() {
        return GovernmentApprovalText;
    }

    public String getGuaranteesText() {
        return GuaranteesText;
    }

    public String getGovernmentLinkText() {
        return GovernmentLinkText;
    }

    public boolean isAirportChange() {
        return IsAirportChange;
    }

    public boolean isDefinedGroundTime() {
        return IsDefinedGroundTime;
    }

    public String getGroundTime() {
        return GroundTime;
    }

    public String getDepDate() {
        return DepDate;
    }

    public String getDepartureDate() {
        return DepartureDate;
    }

    public String getDepartureTime() {
        return DepartureTime;
    }

    public String getArrDate() {
        return ArrDate;
    }

    public String getArrivalTime() {
        return ArrivalTime;
    }

    public String getFlightString() {
        return FlightString;
    }

    public boolean isIntermediateStop() {
        return IsIntermediateStop;
    }

    public String getOrigin() {
        return Origin;
    }

    public String getDestination() {
        return Destination;
    }

    public String getOriginTLC() {
        return OriginTLC;
    }

    public String getDestinationTLC() {
        return DestinationTLC;
    }

    public String getOriginCountryCode() {
        return OriginCountryCode;
    }

    public String getOriginCountry() {
        return OriginCountry;
    }

    public String getOriginContinetCode() {
        return OriginContinetCode;
    }

    public String getDestinationCountryCode() {
        return DestinationCountryCode;
    }

    public String getDestinationCountry() {
        return DestinationCountry;
    }

    public String getDestinationContinentCode() {
        return DestinationContinentCode;
    }

    public String getFlightTimeFormated() {
        return FlightTimeFormated;
    }

    public String getEquipmentCode() {
        return EquipmentCode;
    }

    public int getSalesFlightType() {
        return SalesFlightType;
    }

    public boolean isBusOperated() {
        return IsBusOperated;
    }

    public boolean isSegmentInfo() {
        return IsSegmentInfo;
    }

    public String getSegmentInfoHtml() {
        return SegmentInfoHtml;
    }

    public String getSegmentInfoHtmlRedesign() {
        return SegmentInfoHtmlRedesign;
    }

    public boolean isCodeShare() {
        return IsCodeShare;
    }

    public String getEquipment() {
        return Equipment;
    }

    public boolean isShouldShowDayDifference() {
        return ShouldShowDayDifference;
    }

    public int getDayDifference() {
        return DayDifference;
    }

    public boolean isIntercontinental() {
        return IsIntercontinental;
    }

    public String getBookingClassCode() {
        return BookingClassCode;
    }

    public String getAircraft() {
        return Aircraft;
    }

    public String getDestinationTerminal() {
        return DestinationTerminal;
    }

    public String getOriginTerminal() {
        return OriginTerminal;
    }

    public List<String> getIntermediateStops() {
        return IntermediateStops;
    }
}
