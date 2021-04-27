import { Category } from "./category";
import { Price } from "./price";

export interface ProductThumbnail {
    id:string,
    title:string,
    price:Price,
    images:Array<string>,
    category:Category
}
