"use client"

import { Search } from 'lucide-react'
import { Card, CardContent, CardFooter } from "@/components/ui/card"
import { Button } from "@/components/ui/button"
import { Badge } from "@/components/ui/badge"
import { ContentItem } from "@/types"

interface ContentCardProps {
  item: ContentItem
}

export function ContentCard({ item }: ContentCardProps) {
  const findSimilarContent = () => {
    // Mock API call to find similar content
    console.log(`Finding similar content for: your article`)
    // Here you would typically make an API call to your backend
  }

  return (
    <Card className="overflow-hidden transition-all duration-300 hover:shadow-lg">
      <CardContent className="p-5 space-y-4">
        {/* <img src={item.thumbnail} alt={item.title} className="w-full h-40 object-cover rounded-md" /> */}
        <h3 className="text-lg font-medium text-foreground">{item.summary}</h3>
        {/* <div className="text-sm text-muted-foreground">{item.description}</div> */}
      </CardContent>
      <CardFooter className="flex items-center justify-between p-5">
        {/* <Badge variant="secondary">{item.category}</Badge> */}
        <Button onClick={findSimilarContent} variant="outline" size="sm">
          Find Similar
          <Search className="ml-2 h-4 w-4" />
        </Button>
      </CardFooter>
    </Card>
  )
}