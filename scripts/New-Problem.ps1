[CmdletBinding()]
param(
    [Parameter(Mandatory = $true)]
    [ValidateRange(1, 9999)]
    [int]$Number,

    [Parameter(Mandatory = $true)]
    [ValidatePattern('^[A-Z][A-Za-z0-9]*$')]
    [string]$Name,

    [Parameter(Mandatory = $true)]
    [string]$Title,

    [Parameter(Mandatory = $true)]
    [ValidateSet('Easy', 'Medium')]
    [string]$Difficulty,

    [Parameter(Mandatory = $true)]
    [string]$Topic
)

$ErrorActionPreference = 'Stop'

$projectRoot = Split-Path -Parent $PSScriptRoot
$numberText = $Number.ToString('000')
$className = "P$numberText$Name"
$mainDirectory = Join-Path $projectRoot 'src\main\java\com\hot100\solution'
$testDirectory = Join-Path $projectRoot 'src\test\java\com\hot100\solution'
$notesDirectory = Join-Path $projectRoot 'notes\problems'
$mainFile = Join-Path $mainDirectory "$className.java"
$testFile = Join-Path $testDirectory "${className}Test.java"
$notesFile = Join-Path $notesDirectory "$className.md"

$targets = @($mainFile, $testFile, $notesFile)
$existing = @($targets | Where-Object { Test-Path -LiteralPath $_ })
if ($existing.Count -gt 0) {
    throw "Refusing to overwrite existing file(s): $($existing -join ', ')"
}

New-Item -ItemType Directory -Path $mainDirectory, $testDirectory, $notesDirectory -Force | Out-Null

$replacement = @{
    '{{NUMBER}}' = $Number.ToString()
    '{{TITLE}}' = $Title
    '{{DIFFICULTY}}' = $Difficulty
    '{{TOPIC}}' = $Topic
    '{{CLASS_NAME}}' = $className
}

function Render-Template {
    param(
        [Parameter(Mandatory = $true)]
        [string]$TemplatePath,

        [Parameter(Mandatory = $true)]
        [string]$OutputPath
    )

    $content = [System.IO.File]::ReadAllText($TemplatePath)
    foreach ($entry in $replacement.GetEnumerator()) {
        $content = $content.Replace($entry.Key, $entry.Value)
    }
    $utf8WithoutBom = New-Object System.Text.UTF8Encoding($false)
    [System.IO.File]::WriteAllText($OutputPath, $content, $utf8WithoutBom)
}

Render-Template -TemplatePath (Join-Path $projectRoot 'templates\Solution.java.tpl') -OutputPath $mainFile
Render-Template -TemplatePath (Join-Path $projectRoot 'templates\SolutionTest.java.tpl') -OutputPath $testFile
Render-Template -TemplatePath (Join-Path $projectRoot 'templates\ProblemNotes.md.tpl') -OutputPath $notesFile

Write-Host "Created $className"
Write-Host "  $mainFile"
Write-Host "  $testFile"
Write-Host "  $notesFile"

