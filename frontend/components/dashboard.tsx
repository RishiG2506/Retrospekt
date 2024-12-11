"use client";
import { useState, useEffect } from 'react';
import { Card, CardContent, CardFooter } from "@/components/ui/card";
import axios from 'axios';
import { Category } from "@/types";
import { Microscope, Palette, Globe, BookOpen, Zap, Cpu, Heart, GraduationCap, Film, Newspaper, Smile, Circle, HelpCircle } from 'lucide-react';

const iconMap = {
  Microscope,
  Palette,
  Globe,
  BookOpen,
  Zap,
  Cpu,
  Heart,
  GraduationCap,
  Film,
  Newspaper,
  Smile,
  Circle,
  HelpCircle
};

interface ContentItem {
  id: number;
  author: string;
  summary: string;
  url: string;
  relevantLinks: { id: { url: string } }[];
}

interface DashboardProps {
  categories: Category[];
}

export function Dashboard({ categories }: DashboardProps) {
  const [contentItems, setContentItems] = useState<ContentItem[]>([]);
  const [selectedCategory, setSelectedCategory] = useState<string>("");

  // Fetch content items for a selected category
  const fetchContentItems = async (category: string) => {
    try {
      const response = await axios.get<ContentItem[]>(`http://localhost:8080/api/content/category/${category}`);
      setContentItems(response.data);
    } catch (error) {
      console.error("Error fetching content items:", error);
    }
  };

  // Handle the "View all" button click
  const handleViewAllClick = (category: string) => {
    setSelectedCategory(category);
    fetchContentItems(category);
  };

  return (
    <div className="space-y-6">
      <h2 className="text-2xl font-semibold text-foreground">Your Categories</h2>

      {/* Categories Grid */}
      <div className="grid grid-cols-1 gap-6 sm:grid-cols-2 lg:grid-cols-3">
        {categories.map((category) => {
          const Icon = iconMap[category.icon as keyof typeof iconMap];
          return (
            <Card key={category.id} className="overflow-hidden transition-transform duration-300 hover:scale-105">
              <CardContent className="p-5">
                <div className="flex items-center">
                  <div className="flex-shrink-0">
                    <Icon className="h-6 w-6 text-muted-foreground" />
                  </div>
                  <div className="ml-5 w-0 flex-1">
                    <dl>
                      <dt className="text-sm font-medium text-muted-foreground truncate">
                        {category.name}
                      </dt>
                      <dd>
                        <div className="text-lg font-medium text-foreground">
                          {category.count} items
                        </div>
                      </dd>
                    </dl>
                  </div>
                </div>
              </CardContent>
              <CardFooter className="bg-muted px-5 py-3">
                <div className="text-sm">
                  <button
                    onClick={() => handleViewAllClick(category.name)}
                    className="font-medium text-primary hover:text-primary/80"
                  >
                    View all
                  </button>
                </div>
              </CardFooter>
            </Card>
          );
        })}
      </div>

      {/* Display Content Items for Selected Category */}
      {selectedCategory && (
        <div className="mt-8">
          <h3 className="text-xl font-semibold text-foreground mb-4">Content for "{selectedCategory}" Category:</h3>
          <div className="grid grid-cols-1 gap-6 sm:grid-cols-2 lg:grid-cols-3">
            {contentItems.map((item) => (
              <Card key={item.id} className="overflow-hidden transition-transform duration-300 hover:scale-105">
                <CardContent className="p-5">
                  <p className="text-sm text-muted-foreground mb-2">By {item.author}</p>
                  <p className="text-sm mb-4">{item.summary}</p>
                  {item.relevantLinks && item.relevantLinks.length > 0 && (
                    <div>
                      <h5 className="font-semibold text-sm mb-2">Similar:</h5>
                      <ul className="list-disc list-inside text-sm">
                        {item.relevantLinks.slice(0, 5).map((link, index) => (
                          <li key={index}>
                            <a
                              href={link.id.url}
                              className="text-primary hover:underline"
                              target="_blank"
                              rel="noopener noreferrer"
                            >
                              {new URL(link.id.url).hostname}
                            </a>
                          </li>
                        ))}
                      </ul>
                    </div>
                  )}
                  <a
                    href={item.url}
                    className="inline-block bg-primary text-primary-foreground px-4 py-2 rounded hover:bg-primary/90 transition-colors mt-4 mb-4"
                  >
                    Read complete Tweet
                  </a>

                </CardContent>
              </Card>
            ))}
          </div>
        </div>
      )}


    </div>
  );
}