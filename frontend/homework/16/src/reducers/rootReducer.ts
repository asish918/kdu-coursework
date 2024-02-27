import { combineReducers } from '@reduxjs/toolkit';
import todoReducer from './todoSlice';
import searchReducer from './searchSlice';

const rootReducer = combineReducers({
  todo: todoReducer,
  search: searchReducer
});

export default rootReducer;
