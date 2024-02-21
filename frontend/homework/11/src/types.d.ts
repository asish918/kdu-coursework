export type QuotesApi = {
    _id: string;
    content: string;
    author: string;
    tags: string[];
    authorSlug: string;
    length: number;
    dateAdded: string;
    dateModified: string;
}

export type QuoteFilter = {
    id: string;
    tag: string;
}