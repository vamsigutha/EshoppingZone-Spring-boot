import { Category } from "./category";
import { Price } from "./price";
import { ProductInformation } from "./product-information";
import { ReviewsData } from "./reviews-data";

export interface ProductDetails {
    id:string,
    merchantId:string,
    title:string,
    description:string,
    feature_bullets:Array<string>,
    category:Category,
    delivery_message:string,
    total_images:number,
    images:Array<string>,
    item_available:boolean,
    price:Price,
    product_information:ProductInformation,
    reviews:ReviewsData

}
