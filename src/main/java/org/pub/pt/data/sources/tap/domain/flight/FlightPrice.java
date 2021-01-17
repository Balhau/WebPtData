package org.pub.pt.data.sources.tap.domain.flight;

import java.util.Map;

public class FlightPrice {
    private final String LegId;
    private final int BrandedProductGroupId;
    private final String BrandedProductName;
    private final boolean IsSelected;
    private final boolean IsLastSeat;
    private final int Price;
    private final String Saving;
    private final String CommercialPrice;
    private final Map<String,String> LastSeatCollection;
    private final boolean Enabled;
    private final boolean IsRecommended;
    private final boolean ShowImage;
    private final boolean IsCodeshare;
    private final boolean IsNegotiated;
    private final String BrandedProductFeaturesHtml;
    private final String RecommendationId;
    private final int PenaltyChangesId;
    private final int PenaltyRefundId;
    private final boolean IsNotAvailableLabelException;
    private final boolean IsBusinessClassAvailable;
    private final int MilesAndGoDiscount;
    private final int MilesAndGoDiscountMiles;
    private final boolean IsAvailable;

    public FlightPrice(String legId, int brandedProductGroupId, String brandedProductName, boolean isSelected, boolean isLastSeat, int price,
                       String saving, String commercialPrice, Map<String, String> lastSeatCollection, boolean enabled, boolean isRecommended,
                       boolean showImage, boolean isCodeshare, boolean isNegotiated, String brandedProductFeaturesHtml, String recommendationId,
                       int penaltyChangesId, int penaltyRefundId, boolean isNotAvailableLabelException, boolean isBusinessClassAvailable,
                       int milesAndGoDiscount, int milesAndGoDiscountMiles, boolean isAvailable) {
        LegId = legId;
        BrandedProductGroupId = brandedProductGroupId;
        BrandedProductName = brandedProductName;
        IsSelected = isSelected;
        IsLastSeat = isLastSeat;
        Price = price;
        Saving = saving;
        CommercialPrice = commercialPrice;
        LastSeatCollection = lastSeatCollection;
        Enabled = enabled;
        IsRecommended = isRecommended;
        ShowImage = showImage;
        IsCodeshare = isCodeshare;
        IsNegotiated = isNegotiated;
        BrandedProductFeaturesHtml = brandedProductFeaturesHtml;
        RecommendationId = recommendationId;
        PenaltyChangesId = penaltyChangesId;
        PenaltyRefundId = penaltyRefundId;
        IsNotAvailableLabelException = isNotAvailableLabelException;
        IsBusinessClassAvailable = isBusinessClassAvailable;
        MilesAndGoDiscount = milesAndGoDiscount;
        MilesAndGoDiscountMiles = milesAndGoDiscountMiles;
        IsAvailable = isAvailable;
    }

    public String getLegId() {
        return LegId;
    }

    public int getBrandedProductGroupId() {
        return BrandedProductGroupId;
    }

    public String getBrandedProductName() {
        return BrandedProductName;
    }

    public boolean isSelected() {
        return IsSelected;
    }

    public boolean isLastSeat() {
        return IsLastSeat;
    }

    public int getPrice() {
        return Price;
    }

    public String getSaving() {
        return Saving;
    }

    public String getCommercialPrice() {
        return CommercialPrice;
    }

    public Map<String, String> getLastSeatCollection() {
        return LastSeatCollection;
    }

    public boolean isEnabled() {
        return Enabled;
    }

    public boolean isRecommended() {
        return IsRecommended;
    }

    public boolean isShowImage() {
        return ShowImage;
    }

    public boolean isCodeshare() {
        return IsCodeshare;
    }

    public boolean isNegotiated() {
        return IsNegotiated;
    }

    public String getBrandedProductFeaturesHtml() {
        return BrandedProductFeaturesHtml;
    }

    public String getRecommendationId() {
        return RecommendationId;
    }

    public int getPenaltyChangesId() {
        return PenaltyChangesId;
    }

    public int getPenaltyRefundId() {
        return PenaltyRefundId;
    }

    public boolean isNotAvailableLabelException() {
        return IsNotAvailableLabelException;
    }

    public boolean isBusinessClassAvailable() {
        return IsBusinessClassAvailable;
    }

    public int getMilesAndGoDiscount() {
        return MilesAndGoDiscount;
    }

    public int getMilesAndGoDiscountMiles() {
        return MilesAndGoDiscountMiles;
    }

    public boolean isAvailable() {
        return IsAvailable;
    }
}
