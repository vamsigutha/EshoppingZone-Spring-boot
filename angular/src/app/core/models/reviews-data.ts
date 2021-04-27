import { ReviewDetails } from "./review-details";
import { StarsStat } from "./stars-stat";

export interface ReviewsData {
    result:Array<ReviewDetails>,
    stars_stat:StarsStat,
    total_reviews:string
}
