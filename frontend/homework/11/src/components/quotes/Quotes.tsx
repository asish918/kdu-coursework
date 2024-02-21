import { QuoteFilter, QuotesApi } from "../../types"

import './Quotes.scss'

interface QuotesProps {
    quote: QuotesApi;
    filterList: QuoteFilter[];
    setFilterList: React.Dispatch<React.SetStateAction<QuoteFilter[]>>;
}

export default function Quotes({ quote, filterList, setFilterList }: Readonly<QuotesProps>) {
    function addTags(tag: string) {
        if (filterList.some(item => item.tag === tag)) return;

        const filterTag: QuoteFilter = {
            id: filterList.length === 0 ? "0" : filterList.length.toString(),
            tag: tag
        }

        setFilterList([...filterList, filterTag]);
    }

    return (
        <div className="quotes">
            <p className="quotes__content">{quote.content}</p>
            <p className="quotes__author">
                {quote.author}
                <p className="quotes__date">{quote.dateAdded}</p>
            </p>

            <div className="quotes__tags">
                {quote.tags.map(tag => (
                    <div key={tag} onClick={() => addTags(tag)} className="quotes__tag">
                        {tag}
                    </div>
                ))}
            </div>
        </div>
    )
}