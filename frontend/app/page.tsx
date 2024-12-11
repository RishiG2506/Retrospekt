import { Navbar } from "@/components/navbar"
import { Header } from "@/components/header"
import { Dashboard } from "@/components/dashboard"
import { getCategories } from "@/lib/api"

export default async function Home() {
  const categories = await getCategories();

  return (
    <div className="min-h-screen bg-background">
      <Navbar />
      <Header />
      <main className="container mx-auto py-6 px-4 sm:px-6 lg:px-8">
        <Dashboard categories={categories} />
      </main>
    </div>
  )
}

