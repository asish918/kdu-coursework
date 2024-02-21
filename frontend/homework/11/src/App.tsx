import { useEffect, useRef, useState } from "react";
import { QuoteFilter, QuotesApi } from "./types";
import { gsap } from 'gsap';
import Quotes from "./components/quotes/Quotes";

import './App.scss'
import Spinner from "./components/spinner/Spinner";

function App() {
  const containerRef = useRef(null);
  const buttonRef = useRef(null);

  const [allQuotes, setAllQuotes] = useState<QuotesApi[]>([]);
  const [filterList, setFilterList] = useState<QuoteFilter[]>([]);
  const [filterQuotes, setFilterQuotes] = useState<QuotesApi[]>([]);

  const [loading, setLoading] = useState(false);

  function filterResults(mainArray: QuotesApi[], searchArray: QuoteFilter[]): QuotesApi[] {
    const searchTags = searchArray.map(filter => filter.tag);

    return mainArray.filter(item =>
      item.tags.some(tag => searchTags.includes(tag))
    );
  }

  useEffect(() => {
    const container = containerRef.current;
    const button = buttonRef.current;

    gsap.fromTo(button, { x: 100 }, { x: 0, duration: 1 });
    gsap.fromTo(container, { y: 100 }, { y: 0, duration: 1 });
  }, []);

  useEffect(() => {
    async function apiCall(): Promise<void> {
      const res = await fetch("https://api.quotable.io/quotes/random?limit=3");
      const data: QuotesApi[] = await res.json();

      setAllQuotes(data);
      console.log(data);
    }

    apiCall();
  }, []);

  useEffect(() => {
    if (filterList.length === 0) {
      setFilterQuotes(allQuotes);
      return;
    }

    setFilterQuotes(filterResults(allQuotes, filterList));
  }, [filterList, allQuotes])

  async function newQuoteHandler(): Promise<void> {
    setLoading(true);
    const res = await fetch("https://api.quotable.io/quotes/random");
    const data: QuotesApi[] = await res.json();

    setAllQuotes([...allQuotes, data[0]]);
    setFilterQuotes([...allQuotes, data[0]]);
    setLoading(false);
  }

  function removeFilter(id: string) {
    console.log(filterList);
    setFilterList(filterList.filter(item => item.id !== id));
  }

  return (
    <main className="main">
      <button ref={buttonRef} onClick={() => {
        newQuoteHandler().then(() => {
          window.scrollTo({
            top: document.documentElement.scrollHeight,
            behavior: 'smooth',
          });
        })
      }} className="main__button">New Quote
        {loading && <Spinner />}
      </button>

      <section className="main__filter">
        <h1>Filters</h1>

        {filterList.map(item => (
          <div key={item.id} className="main__tag">
            <p>{item.tag}</p>
            <button onClick={() => removeFilter(item.id)}>X</button>
          </div>
        ))}
      </section>

      <section ref={containerRef} className="main__quotes-container">
        {filterQuotes.map(item => (
          <Quotes key={item._id} quote={item} setFilterList={setFilterList} filterList={filterList} />
        ))}
      </section>
    </main>
  )
}

export default App
