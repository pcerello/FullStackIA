import React from 'react';
import { render, screen } from '@testing-library/react';
import App from './App'; // Assure-toi que le chemin vers App est correct
import '@testing-library/jest-dom/extend-expect'; // Assure-toi d'importer avec le chemin correct

test('renders learn react link', () => {
  render(<App />);
  const linkElement = screen.getByText(/learn react/i);
  expect(linkElement).toBeInTheDocument();
});
