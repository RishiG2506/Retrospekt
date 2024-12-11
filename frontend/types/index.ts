export interface Category {
  id: number;
  name: string;
  icon: string;
  count: number;
}

// export interface ContentItem {
//   id: number;
//   title: string;
//   description: string;
//   category: string;
//   thumbnail: string;
// }

export interface ContentItem {
  id: number;
  // title: string;
  author: string;
  summary: string; // Ensure summary is included
  url: string; // Ensure url is included
}