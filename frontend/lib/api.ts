import { Category } from "@/types";

export async function getCategories(): Promise<Category[]> {
  const categories: Omit<Category, "count">[] = [
    { id: 1, name: "Technology", icon: "Cpu" },
    { id: 2, name: "Health and Wellness", icon: "Heart" },
    { id: 3, name: "Education and Career", icon: "GraduationCap" },
    { id: 4, name: "Entertainment and Lifestyle", icon: "Film" },
    { id: 5, name: "News and Politics", icon: "Newspaper" },
    { id: 6, name: "Humor and Memes", icon: "Smile" },
    { id: 7, name: "Others", icon: "Circle" },
  ];

  const apiBaseUrl = "http://localhost:8080/api/content/category/count";

  const updatedCategories: Category[] = await Promise.all(
    categories.map(async (category) => {
      try {
        const response = await fetch(`${apiBaseUrl}/${category.name}`);
        const count = await response.json();
        return { ...category, count: count as number };
      } catch (error) {
        console.error(`Failed to fetch count for category ${category.name}:`, error);
        return { ...category, count: 0 }; // Default to 0 if API call fails
      }
    })
  );

  return updatedCategories;
}
